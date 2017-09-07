import com.taotao.utils.FastDFSClient;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by xingguo on 2017-09-06 08:47.
 *
 * @author mengy
 * @project taotao
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:")*/
public class TestFastDfs {

    @Test
    public void testD() throws Exception {
        /*//创建fastdfs.conf ,配置文件就是fastdfs的相关信息
        //2加载配置文件
        ClientGlobal.init("L:\\taotao\\taotao_manager_web\\src\\main\\resources\\FastDfs.conf");
        //3.创建一个trackerclient 对象
        TrackerClient trackerClient = new TrackerClient();
        //4:通过TrackerClient 对象获得TrackerServer对象
        TrackerServer connection = trackerClient.getConnection();
        //5:创建一个StorageServer 的引用 .null
        StorageServer storageServer=null;
        //创建一个StorageClient 对象.需要两个参数TrackerServer,StorageServer
        StorageClient storageClient = new StorageClient(connection, storageServer);
        //使用该对象
        //参数1:文件名 参数2:后缀名 参数三:文件的元数据
        String[] pngs = storageClient.upload_file("C:\\Users\\mengy\\Desktop\\image\\1.PNG", "png", null);
        for (String s : pngs){
            System.out.println(s);
        }*/


    }

    @Test
    public void testFastDfsUtils() throws Exception{
       /* FastDFSClient fastDFSClient=new FastDFSClient("L:\\taotao\\taotao_manager_web\\src\\main\\resources\\FastDfs.conf");
        String s = fastDFSClient.uploadFile("C:\\Users\\mengy\\Desktop\\image\\2.bmp");
        System.out.println(s);*/
    }

}
