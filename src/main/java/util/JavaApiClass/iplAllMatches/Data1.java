package util.JavaApiClass.iplAllMatches;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data1 {
    private SeriesInfo info;
    private List<Match> matchList;
}

