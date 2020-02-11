package officeTest.watermark;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.flink.streaming.api.watermark.Watermark;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyEvent {
    private long creationTime;
    private Watermark watermark;

    public boolean hasWatermarkMarker(){
        if(null == watermark){
            return false;
        }
        return true;
    }

}
