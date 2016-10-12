import java.lang.Exception;
import static java.lang.Math.abs;

class StyleRanking{

    public static final int MAX_RANK = 8;
    public int rank = -8;
    public int progress = 0;

    public void incProgress(int activityRank)  throws IllegalArgumentException{
        if (activityRank == 0 || activityRank > 8 || activityRank < -8) {

            throw new IllegalArgumentException();

        }
        if(this.rank==8){
        this.progress=0;
        }
        else if (this.rank == activityRank) {
            progress += 3;
        } else if (this.rank == activityRank + 1||(activityRank==-1&&this.rank==activityRank+2)) {
            progress += 1;
        } else if (this.rank > activityRank + 1) {
            progress += progress;
        } else {
            int rankDiff = abs(this.rank - activityRank);
            if ((activityRank > 0 && this.rank < 0)||(activityRank < 0 && this.rank > 0)) {
                rankDiff -= 1;
            }
            int multiplier = rankDiff * rankDiff;
            progress += (10 * multiplier);
        }
      
        while (progress >= 100) {
            this.progress = progress - 100;
            if (rank == -1) {
                rank += 2;
            } else if (rank == 8) {

            } else {
                rank++;
            }

        }
          if(this.rank==8){
        this.progress=0;
        }
      System.out.println("rank:"+this.rank+"---"+"progress: "+this.progress);

    }

}