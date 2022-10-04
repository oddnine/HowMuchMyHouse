package toyproject.hmmh.getdata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import toyproject.hmmh.domain.RegionCodeItem;
import toyproject.hmmh.item.RegionCodeItemRepository;

import java.io.*;
import java.net.URL;

@Slf4j
public class GetTxtData {
    @Autowired
    private RegionCodeItemRepository regionCodeItemRepository;

    public void saveRegionCodes() {
        try {
            URL resource = getClass().getClassLoader().getResource("AddressCodeList.txt");
            File file = ResourceUtils.getFile(resource.getFile());
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            BufferedReader bufferedReader = new BufferedReader(br);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("폐지")) {
                    continue;
                } else {
                    String[] split = line.split("\t");
                    regionCodeItemRepository.save(new RegionCodeItem(split[0], split[1]));
                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            log.error("IOException", e);
        }
    }
}
