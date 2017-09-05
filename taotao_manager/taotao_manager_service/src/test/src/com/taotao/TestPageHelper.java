package com.taotao;

import com.github.pagehelper.PageInfo;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import com.taotao.service.impl.ItemServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by xingguo on 2017-09-04 19:43.
 *
 * @author mengy
 * @project taotao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/spring-config-*.xml")
public class TestPageHelper {

    @Autowired
    private ItemService itemService;

    @Test
    public void testPage(){

    }

}
