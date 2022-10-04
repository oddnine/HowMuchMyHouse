package toyproject.hmmh.item;

import org.springframework.stereotype.Repository;
import toyproject.hmmh.domain.RegionCodeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RegionCodeItemRepositoryImpl implements RegionCodeItemRepository{
    private static final Map<String, RegionCodeItem> regionStore = new ConcurrentHashMap<>();

    @Override
    public RegionCodeItem save(RegionCodeItem regionCodeItem) {
        regionStore.put(regionCodeItem.getRegionName(), regionCodeItem);
        return regionCodeItem;
    }

    @Override
    public RegionCodeItem findBySigunguCode(String address) {
        return regionStore.get(address);
    }

    @Override
    public List<RegionCodeItem> findAll() {
        return new ArrayList<>(regionStore.values());
    }

    @Override
    public void clearStore() {
        regionStore.clear();
    }
}
