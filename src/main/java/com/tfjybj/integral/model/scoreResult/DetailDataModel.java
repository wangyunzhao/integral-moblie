package com.tfjybj.integral.model.scoreResult;

import com.tfjybj.integral.model.scoreResult.DetailBugModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

//import javax.xml.soap.Detail;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class DetailDataModel {

    /**
     * bug各状态详细数据
     */
    private List<DetailBugModel> detailBugSet;
    /**
     * Task各状态详细数据
     */
    private List<DetailTaskModel> detailTaskSet;

}
