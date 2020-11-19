import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.util.*;

//使用方法基本和命令一样
public class RedisDemo {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("192.168.104.62",6379);

        //数据存储
        Article a1 = new Article(1, "t01", "c01");
        Article a2 = new Article(2, "t02", "c02");
        Article a3 = new Article(3, "t03", "c03");
        List<Article> articles = Arrays.asList(a1, a2, a3);
        //集合转json
        String json = JSON.toJSONString(articles);
        //初始化一次 有且只有一次
        jedis.set("articles",json);

        //判断文章是否存在，用户是否已投票，更新分数
        Scanner input =new Scanner(System.in);
        while (true){
            //显示列表
            String jsonart = jedis.get("articles");
            List<Article> list = JSON.parseArray(json, Article.class);
            ArrayList aid = new ArrayList();
            for (Article art:list) {
                System.out.println(art);
                //手机文章ID
                    aid.add(art.getId());
            }

            System.out.println("请输出要投票的文章编号，退出-1");
            int id = input.nextInt();
            if (id==-1)break;
            if (!aid.contains(id)){
                System.out.println("此文章不存在");
                continue;
            }
            System.out.println("请输入你的ID");
            int nameid = input.nextInt();
            Long result = jedis.sadd("vote", id + ":" + nameid);
            if (result==1) {
                System.out.println("感谢你的投票");
                //得分加10
                Article article = list.get(id - 1);
                article.setScore(article.getScore()+10);
                //更新文章数据
                jedis.set("articles",JSON.toJSONString(list));
            }else {
                System.out.println("亲 你已经投过票了 ，请不要重复投票");
            }

        }
    }
}
