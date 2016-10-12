import java.util.List;

class PaginationHelper<I> {

    List<I> collection;
    int itemsPerPage;

    /**
     * The constructor takes in an array of items and a integer indicating how
     * many items fit within a single page
     */
    public PaginationHelper(List<I> collection, int itemsPerPage) {
        this.collection = collection;
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return collection.size();
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {

        return (int) Math.ceil((double) collection.size() / itemsPerPage);

    }

    /**
     * returns the number of items on the current page. page_index is zero
     * based. this method should return -1 for pageIndex values that are out of
     * range
     */
    public int pageItemCount(int pageIndex) {
        if (pageIndex > pageCount() - 1) {
            return -1;
        } else if (pageIndex == pageCount() - 1) {

            return collection.size() % itemsPerPage;
        } else {
            return itemsPerPage;
        }
    }

    /**
     * determines what page an item is on. Zero based indexes this method should
     * return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
     if(collection.size()==0){
            return  -1;
        }
        if (itemIndex+1 > collection.size()||itemIndex<0) {
            return -1;
        } else {
           return (itemIndex+1)/itemsPerPage;
        }
    }
}