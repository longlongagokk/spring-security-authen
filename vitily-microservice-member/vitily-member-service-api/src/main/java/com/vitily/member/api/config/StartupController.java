package com.vitily.member.api.config;

import com.vitily.cache.service.CommonServiceCache;
import com.vitily.common.consts.CommonEnumContainer;
import com.vitily.common.consts.DictionaryKey;
import com.vitily.common.module.Result;
import com.vitily.common.util.CommonUtil;
import com.vitily.common.util.JSONUtil;
import com.vitily.member.module.search.TsMemGroup;
import com.vitily.member.module.view.TvGroupHasResources;
import com.vitily.member.module.view.TvMemGroup;
import com.vitily.member.server.service.MemGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Configuration
@ComponentScan({"com.vitily.member.api.config"})
@Slf4j
public class StartupController implements CommandLineRunner {
    @Value("${spring.application.name}")
    String applicationName;
    @Value("${spring.cloud.config.profile}")
    String projectEnv;
    @Value("${server.port}")
    String serverPort;
    @Value("${management.server.port}")
    String managePort;
    @Autowired
    MemGroupService memGroupService;
    @Autowired
    CommonServiceCache commonServiceCache;

    @Override
    public void run(String... args) throws Exception {
        try {
            log.info(">>>>>>>>>>>>>>>服务启动执行,端口："+serverPort+",管理端口:"+managePort+"，执行加载数据["+applicationName+","+projectEnv+"]等操作<<<<<<<<<<<<<");
            loadGroupResources();
            log.info("------初始化工作完毕-----");
        }catch (Exception e){
            Result result = CommonUtil.getResultByThrowable(e);
            log.error("------初始化工作错误,详细信息："+ JSONUtil.toJSONString(result.getMessage())+"-----");
        }
    }
    private void loadGroupResources(){
        CommonServiceCache groupResourcesCache = commonServiceCache.getInstance(DictionaryKey.Keys.会员分组拥有的资源);
        groupResourcesCache.removeFromServer(DictionaryKey.Keys.会员分组拥有的资源.getValue());
        TsMemGroup query = new TsMemGroup();
        query.getEntity().setState(CommonEnumContainer.State.正常.getValue());
        List<TvGroupHasResources> groupList = memGroupService.getOwnResources(query);
        Map<String,List<String>> maps = new HashMap<>();
        for(TvGroupHasResources g:groupList){
            String _key = String.valueOf(g.getGroupId());
            List<String> _values;
            if(!maps.containsKey(_key)){
                _values = new ArrayList<>();
            }else{
                _values = maps.get(_key);
            }
            _values.add(g.getUrlPattern());
            maps.put(_key,_values);
        }
        if(!maps.isEmpty()){
            groupResourcesCache.hmset(DictionaryKey.Keys.会员分组拥有的资源.getValue(),maps);
        }
    }
















}
