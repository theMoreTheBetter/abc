package officeTest.watermark;

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import javax.annotation.Nullable;

public class TimeLagWatermarkGenerator implements AssignerWithPeriodicWatermarks<MyEvent> {
    private final long maxTimeLag = 5000; // 5 seconds

    @Nullable
    @Override
    public Watermark getCurrentWatermark() {
        return new Watermark(System.currentTimeMillis() - maxTimeLag);
    }

    @Override
    public long extractTimestamp(MyEvent myEvent, long l) {
        return myEvent.getCreationTime();
    }
}
