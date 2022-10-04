package toyproject.hmmh.item;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import toyproject.hmmh.domain.Apt;
import toyproject.hmmh.domain.UserApt;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AptRepositoryImpl implements AptRepository {

    private final JdbcTemplate template;

    public AptRepositoryImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }


    @Override
    public int save(Apt apt) {
        String sql = "insert into hmmh (AMOUNT, BUILD_YEAR, DEAL_YEAR, DEAL_MONTH, DEAL_DAY, SIGUNGU_CODE, EUPMYUNDONG_CODE, SIGUNGU, DONG, APT_NAME, DEDICATED_AREA, FLOOR) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        List<Object> param = new ArrayList<>();
        param.add(apt.getAmount());
        param.add(apt.getBuildYear());
        param.add(apt.getDealYear());
        param.add(apt.getDealMonth());
        param.add(apt.getDealDay());
        param.add(apt.getSigunguCode());
        param.add(apt.getEupmyundongCode());
        param.add(apt.getSigungu());
        param.add(apt.getDong());
        param.add(apt.getAptName());
        param.add(apt.getDedicatedArea());
        param.add(apt.getFloor());

        return template.update(sql, param.toArray());
    }

    @Override
    public List<Apt> findByInfo(UserApt userApt, int dealYear, int dealMonth) {
        UserApt tempApt = new UserApt(userApt.getSigungu(), userApt.getDong(), userApt.getAptName());
        String sql = "select * from hmmh where SIGUNGU like concat('%', ?,'%') and DONG = ? and APT_NAME like concat('%', ?,'%') and DEAL_YEAR = ? and DEAL_MONTH = ?";

        if (tempApt.getSigungu().contains("특별시")) {
            tempApt.setSigungu(tempApt.getSigungu().replace("특별시", ""));
        }

        List<Object> param = new ArrayList<>();
        param.add(tempApt.getSigungu());
        param.add(tempApt.getDong());
        param.add(tempApt.getAptName());
        param.add(dealYear);
        param.add(dealMonth);

        return template.query(sql, itemRowMapper(), param.toArray());
    }

    private RowMapper<Apt> itemRowMapper() {
        return (rs, rowNum) -> {
            Apt item = new Apt();
            item.setAmount(rs.getInt("AMOUNT"));
            item.setBuildYear(rs.getInt("BUILD_YEAR"));
            item.setDealYear(rs.getInt("DEAL_YEAR"));
            item.setDealMonth(rs.getInt("DEAL_MONTH"));
            item.setDealDay(rs.getInt("DEAL_DAY"));
            item.setSigunguCode(rs.getInt("SIGUNGU_CODE"));
            item.setEupmyundongCode(rs.getInt("EUPMYUNDONG_CODE"));
            item.setSigungu(rs.getString("SIGUNGU"));
            item.setDong(rs.getString("DONG"));
            item.setAptName(rs.getString("APT_NAME"));
            item.setDedicatedArea(rs.getDouble("DEDICATED_AREA"));
            item.setFloor(rs.getInt("FLOOR"));
            return item;
        };
    }
}


