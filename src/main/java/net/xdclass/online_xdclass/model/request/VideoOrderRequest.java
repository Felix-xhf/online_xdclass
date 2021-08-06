package net.xdclass.online_xdclass.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @description:
 * @author: Felix_XHF
 * @create:2021-07-30 11:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoOrderRequest {
    @JsonProperty("video_id")
    private int videoId;
}
