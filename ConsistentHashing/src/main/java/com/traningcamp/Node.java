package com.traningcamp;

import java.util.AbstractMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Node {

    private ConcurrentSkipListMap<Integer, String> skipListHash;


    // 构建 Hash 环
    public AbstractMap<Integer, String> buildHash(ConcurrentSkipListMap<Integer, String> skipListHash) {
        this.skipListHash = skipListHash;
        return skipListHash;
    }

    public void addServerNode(String serverNodeName) {
        int hash = new GetHashCode().getHashCode(serverNodeName);
        skipListHash.put(hash, serverNodeName);
    }

    public void delServerNode(String serverNodeName) {
        int hash = new GetHashCode().getHashCode(serverNodeName);
        skipListHash.remove(hash);
    }

    public String selectServerNode(String requestURL) {
        int hash = new GetHashCode().getHashCode(requestURL);
        ConcurrentNavigableMap<Integer, String> tailMap = skipListHash.tailMap(hash);
        String VNNode = tailMap.isEmpty() ? skipListHash.firstEntry().getValue() : tailMap.firstEntry().getValue();

//        // 向右寻找第一个 key
//        Map.Entry<Integer, String> subEntry = skipListHash.ceilingEntry(hash);
//        // 设置成一个环，如果超过尾部，则取第一个点
//        subEntry = subEntry == null ? skipListHash.firstEntry() : subEntry;
//        String VNNode = subEntry.getValue();
        return VNNode.substring(0, VNNode.indexOf("&&"));
    }

}
