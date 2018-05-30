package id.kostalk.app.ui.member_profile;

import id.kostalk.app.di.PerActivity;
import id.kostalk.app.ui.base.MvpPresenter;

@PerActivity
public interface MemberProfileMvpPresenter<V extends MemberProfileMvpView> extends MvpPresenter<V> {

    void onDeleteClick(long memberId);
}
