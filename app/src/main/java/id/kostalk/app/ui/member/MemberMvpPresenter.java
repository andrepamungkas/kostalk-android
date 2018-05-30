package id.kostalk.app.ui.member;

import id.kostalk.app.di.PerActivity;
import id.kostalk.app.ui.base.MvpPresenter;

@PerActivity
public interface MemberMvpPresenter <V extends MemberMvpView> extends MvpPresenter<V> {

    void onGetMemberList();
}
