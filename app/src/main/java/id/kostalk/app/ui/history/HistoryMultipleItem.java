package id.kostalk.app.ui.history;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import id.kostalk.app.data.remote.model.History;

/**
 * Created by andre on 3/25/2018.
 */

public class HistoryMultipleItem implements MultiItemEntity {

    public static final int TYPE_HEADER = 1;
    public static final int TYPE_HISTORY_IN = 2;
    public static final int TYPE_HISTORY_OUT = 3;
    private int itemType;
    private String header;
    private History history;

    public HistoryMultipleItem(int itemType, String header) {
        this.itemType = itemType;
        this.header = header;
    }

    public HistoryMultipleItem(int itemType, History history) {
        this.itemType = itemType;
        this.history = history;
    }

    public HistoryMultipleItem(int itemType) {
        this.itemType = itemType;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
