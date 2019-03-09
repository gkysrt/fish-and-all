package com.example.android.fishall;

/**
 * Created by ALLDe on 14-May-18.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by ALLDe on 30-Apr-18.
 */

public class EncyclopediaAdapter extends RecyclerView.Adapter<EncyclopediaAdapter.EncyclopediaViewHolder> {

    private static final String TAG = EncyclopediaAdapter.class.getSimpleName();
    private final String[] fishNames = {
            "Barbunya","Çipura","Hamsi","İstavrit","Kefal","Kupes","Levrek","Lüfer","Mezgit","Orkinos",
            "Palamut","Sardalya","Uskumru","Kalkan","Mercan","Kılıç Balığı","Tekir","Trança","Kırlangıç","Sinarit"
    };
    private final String[] fishLatin = {
            "Mullus Barbutus", "Sparus Aurata", "Engraulis Encrasicolus", "Trachurus Mediterraneus", "Mugilidae",
            "Boops Boops", "Dicentrarchus", "Pomotomus Saltator","Merlangius Merlangus", "Thunnus Thynnus", "Sarda Sarda",
            "Sardina Pilchardus", "Scomber Scombrus", "Scophthalmus Maximus", "Sparus Pagrus", "Xiphias Gladius", "Mullus Surmuletus",
            "Pagru Ehrenbergi", "Trigla Lucerna", "Dentex Dentex"
    };
    /*
    * An on-click handler that we've defined to make it easy for an Activity to interface with
    * our RecyclerView
    */
    final private ListItemClickListener mOnClickListener;

    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    /**
     * Constructor for Adapter; includes a list item click listener
     *
     * @param listener Listener for list item clicks
     */
    public EncyclopediaAdapter(ListItemClickListener listener)
    {
        mOnClickListener=listener;
    }

    /**
     *
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout.
     * @return A new ArticleViewHolder that holds the View for each list item
     */
    @Override
    public EncyclopediaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        Context context = viewGroup.getContext();
        int layoutIdForListItem=R.layout.encyclopedia_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        EncyclopediaViewHolder viewHolder = new EncyclopediaViewHolder(view);

        return viewHolder;
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    public void onBindViewHolder(EncyclopediaViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(fishNames[position],fishLatin[position]);

    }

    /**
     * This method simply returns 20 items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our encyclopedia
     */
    @Override
    public int getItemCount() {
        return 20;
    }

    class EncyclopediaViewHolder extends RecyclerView.ViewHolder
            implements OnClickListener {

        ImageView icon;
        TextView fishTextView;
        TextView latinTextView;


        public EncyclopediaViewHolder(View itemView) {
            super(itemView);

            fishTextView = (TextView) itemView.findViewById(R.id.fish_name);
            latinTextView = (TextView) itemView.findViewById(R.id.latin);

            itemView.setOnClickListener(this);
        }


        /**
         * Called whenever a user clicks on an item in the list.
         * @param v The View that was clicked
         */
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }

        void bind(String fishName,String latinName){
            fishTextView.setText(fishName);
            latinTextView.setText(latinName);}
    }
}
