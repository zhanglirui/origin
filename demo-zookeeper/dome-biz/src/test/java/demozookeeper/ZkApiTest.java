package demozookeeper;

import java.util.List;

import com.example.demozookeeper.DemoZookeeperApplication;
import com.example.demozookeeper.WatcherApi;
import com.example.demozookeeper.ZkApi;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author zhanglirui
 * @date 2020/12/7 4:16 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoZookeeperApplication.class)
public class ZkApiTest {

    @Autowired
    private ZkApi zkApi;

    @Test
    public void exists() {
        Stat exists = zkApi.exists("/createNode", true);
        System.out.println("结果打印-----"+exists.toString());
    }

    @Test
    public void testExists() {
        Stat exists = zkApi.exists("/createNode", new WatcherApi());
        System.out.println("结果打印-----"+exists.toString());
    }

    @Test
    public void createNode() {
        boolean node = zkApi.createNode("/createNode/childrenNode", "创建节点");
        Assert.assertTrue("创建失败",node);
    }

    @Test
    public void updateNode() {
        boolean node = zkApi.updateNode("/createNode", "修改节点信息");
        Assert.assertTrue("修改失败",node);
    }

    @Test
    public void deleteNode() {
        boolean b = zkApi.deleteNode("/createNode/childrenNode");
    }

    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zkApi.getChildren("/createNode");
        System.out.println(children.get(0));
    }

    @Test
    public void getData() {
        String data = zkApi.getData("/createNode", new WatcherApi());
        System.out.println(data);
    }

    @Test
    public void init() {
    }
}