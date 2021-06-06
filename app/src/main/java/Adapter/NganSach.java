package Adapter;

public class NganSach {
    private int id;
    private long muchi;
    private String thoigian;

    public NganSach(int id, long muchi, String thoigian) {
        this.id = id;
        this.muchi = muchi;
        this.thoigian = thoigian;
    }

    public NganSach(long muchi, String thoigian) {
        this.muchi = muchi;
        this.thoigian = thoigian;
    }

    public int getId() {
        return id;
    }

    public long getMuchi() {
        return muchi;
    }

    public String getThoigian() {
        return thoigian;
    }
}


