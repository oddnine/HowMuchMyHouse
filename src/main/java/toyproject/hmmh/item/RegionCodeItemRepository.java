package toyproject.hmmh.item;

import toyproject.hmmh.domain.RegionCodeItem;

import java.util.List;

public interface RegionCodeItemRepository {
    RegionCodeItem save(RegionCodeItem regionCodeItem);

    RegionCodeItem findBySigunguCode(String sigungu);

    List<RegionCodeItem> findAll();

    void clearStore();
}
