package id.kostalk.app.ui.member_form;

import id.kostalk.app.di.PerActivity;
import id.kostalk.app.ui.base.MvpPresenter;

@PerActivity
public interface MemberFormMvpPresenter <V extends MemberFormMvpView> extends MvpPresenter<V> {

    void onAddClick(String name, String phone, int cost, int interval, String startDate);

    void onUpdateClick(long memberId, String name, String phone, int cost, int interval, String startDate);
}
