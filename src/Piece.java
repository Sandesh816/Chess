public class Piece {
    private int side;
    private int rank;
    public Piece(int side, int rank){
        this.side = side;
        this.rank = rank;
    }

    public int getSide(){
        return this.side;
    }

    public int getRank(){
        return this.rank;
    }

    public String RanktoString() {
        String[] ranks = {"None","Pawn","Knight","Bishop","Rook","Queen","King"};
        return ranks[rank];
    }

    public String RanktoBlackImage() {
        String[] images = {"None","Chess_pdt60.png","Chess_ndt60.png","Chess_bdt60.png","Chess_rdt60.png","Chess_qdt60.png","Chess_kdt60.png"};
        return images[rank];
    }

    public String RanktoWhiteImage() {
        String[] images = {"None","Chess_plt60.png","Chess_nlt60.png","Chess_blt60.png","Chess_rlt60.png","Chess_qlt60.png","Chess_klt60.png"};
        return images[rank];
    }

}
