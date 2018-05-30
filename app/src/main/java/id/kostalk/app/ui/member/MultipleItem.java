package id.kostalk.app.ui.member;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import id.kostalk.app.data.remote.model.Anggota;

/**
 * Created by andre on 3/25/2018.
 */

public class MultipleItem implements MultiItemEntity {

    public static final int TYPE_HEADER = 1;
    public static final int TYPE_ITEM = 2;
    private int itemType;
    private String header;
    private Anggota anggota;

    public MultipleItem(int itemType, String header) {
        this.itemType = itemType;
        this.header = header;
    }

    public MultipleItem(int itemType, Anggota anggota) {
        this.itemType = itemType;
        this.anggota = anggota;
    }

    public MultipleItem(int itemType) {
        this.itemType = itemType;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
