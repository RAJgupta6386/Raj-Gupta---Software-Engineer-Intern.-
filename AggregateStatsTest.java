
package io.cdap.wrangler;

import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.directives.AggregateStats;

public class AggregateStatsTest {
    @Test
    public void testByteSizeParsing() {
        Assert.assertEquals(10240, new ByteSize("10KB").getBytes());
    }

    @Test
    public void testTimeDurationParsing() {
        Assert.assertEquals(2000, new TimeDuration("2s").getMilliseconds());
    }

    @Test
    public void testAggregation() throws Exception {
        List<Row> rows = new ArrayList<>();
        rows.add(new Row("data_transfer_size", "10KB").add("response_time", "2s"));
        rows.add(new Row("data_transfer_size", "20KB").add("response_time", "3s"));

        AggregateStats directive = new AggregateStats();
        directive.initialize(new Arguments(new String[]{"data_transfer_size", "response_time", "total_size_mb", "total_time_sec"}));

        List<Row> result = directive.execute(rows);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(0.029, (Double) result.get(0).getValue("total_size_mb"), 0.001);
        Assert.assertEquals(5.0, (Double) result.get(0).getValue("total_time_sec"), 0.001);
    }
}
