package bdqn.springboot.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot默认支持两种技术和es进行交互
 * 1、jest（默认不生效）
 * 需要导入io.searchbox.client.JestClient工具包
 * 2、Spring Data ElasticSearch[es版本可能不合适，连接超时]
 *     *升级spingboot版本
 *     *安装对应的ES版本
 *   1）Client clusterNodes : clusterName
 *   2)ElasticSearchTemplate 操作es
 *   3)编写一个ElasticserchRepository的子接口来实现es
 *  两种用法https://github.com/spring-projects/spring-data-elasticsearch
 *  1、编写一个ElasticsearchRepository接口
 */
@SpringBootApplication
public class Springboot03ElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03ElasticApplication.class, args);
    }

}
