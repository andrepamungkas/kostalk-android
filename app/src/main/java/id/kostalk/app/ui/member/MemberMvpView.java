package id.kostalk.app.ui.member;

import java.util.List;

import id.kostalk.app.data.remote.model.Anggota;
import id.kostalk.app.ui.base.MvpView;

public interface MemberMvpView extends MvpView {

    void buildMemberList(List<Anggota> anggotaList);
}
