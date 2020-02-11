package officeTest.watermark;

import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import javax.annotation.Nullable;

public class PunctuatedAssigner implements AssignerWithPunctuatedWatermarks<MyEvent> {
    @Nullable
    @Override
    public Watermark checkAndGetNextWatermark(MyEvent myEvent, long l) {
        return myEvent.hasWatermarkMarker() ? new Watermark(l) : null;
    }

    @Override
    public long extractTimestamp(MyEvent myEvent, long l) {
        return myEvent.getCreationTime();
    }
}
