package toyproject.hmmh.item;

import toyproject.hmmh.domain.Apt;
import toyproject.hmmh.domain.UserApt;

import java.util.List;

public interface AptRepository {
    int save(Apt apt);

    List<Apt> findByInfo(UserApt userApt, int dealYear, int dealMonth);
}
