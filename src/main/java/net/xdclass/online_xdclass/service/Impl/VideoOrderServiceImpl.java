package net.xdclass.online_xdclass.service.Impl;

import net.xdclass.online_xdclass.exception.XDException;
import net.xdclass.online_xdclass.mapper.*;
import net.xdclass.online_xdclass.model.entity.Episode;
import net.xdclass.online_xdclass.model.entity.PlayRecord;
import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.model.entity.VideoOrder;
import net.xdclass.online_xdclass.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 * @description:
 * @author: Felix_XHF
 * @create:2021-07-30 11:36
 */
@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private EpisodeMapper episodeMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;

    /*
    * @Description: 下单操作
    * 未来版本：优惠券抵扣，风控用户检查，生成订单基础信息，生成支付信息
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    @Transactional
    @Override
    public int save(int userId, int videoId) {
        //判断是否已经购买
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userId,videoId,1);

        if (videoOrder != null){
            return 0;
        }


        Video video = videoMapper.findById(videoId);
        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState(1);
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setUserId(userId);

        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());

        int row = videoOrderMapper.saveOrder(newVideoOrder);


        //生成播放记录
        if (row == 1){
            Episode episode = episodeMapper.findFirstEpisodeById(videoId);
            if (episode == null){
                throw new XDException(-1,"视频没有集信息，请运行人员检测");
            }

            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);
            playRecordMapper.saveRecord(playRecord);
        }

        return row;
    }

    /*
    * @Description: 查询指定用户的所有订单列表
    * @Author: Mr.Felix
    * @Time: 2021/7/30
    **/
    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {
        return videoOrderMapper.listOrderByUserId(userId);
    }
}
