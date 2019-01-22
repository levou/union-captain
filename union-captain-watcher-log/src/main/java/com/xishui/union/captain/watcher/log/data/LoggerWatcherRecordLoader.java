package com.xishui.union.captain.watcher.log.data;

import com.alibaba.fastjson.JSON;
import com.xishui.union.captain.watcher.data.WatcherRecordLoader;
import com.xishui.union.captain.watcher.log.holder.LoggerHolder;
import com.xishui.union.captain.watcher.log.holder.LoggerHolderKey;
import com.xishui.union.captain.watcher.model.WatcherRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LoggerWatcherRecordLoader implements WatcherRecordLoader {

    private final Logger logger = LoggerFactory.getLogger(LoggerWatcherRecordLoader.class);

    private final Integer MAX_RECORD_SIZE_LOADER = 5000;

    @Override
    public List<WatcherRecord> loadAllWatcherRecord() {
        return loaderData(WatcherDataCondition.newCondition());
    }

    @Override
    public List<WatcherRecord> loadBetweenData(Date startDate, Date endDate) {
        return loaderData(WatcherDataCondition.newCondition().startDate(startDate).endDate(endDate));
    }

    @Override
    public List<WatcherRecord> loadByWatcherId(String watcherId) {
        return loaderData(WatcherDataCondition.newCondition().watcherId(watcherId));
    }


    private List<WatcherRecord> loaderData(WatcherDataCondition condition) {
        try {
            File file = new File(LoggerHolder.HOLDER.get(LoggerHolderKey.LOGGER_PATH));
            if (!file.exists()) {
                // not exist
                throw new NullPointerException("LogPath file is not exist. path:" + LoggerHolder.HOLDER.get(LoggerHolderKey.LOGGER_PATH));
            }
            //不递归，只取当前目录好了
            if (file.isDirectory()) {
                final List<WatcherRecord> watcherRecords = new ArrayList<>();
                List<File> files = Arrays.asList(file.listFiles());
                //文件按照最新
                files.stream().sorted(new Comparator<File>() {
                    @Override
                    public int compare(File o1, File o2) {
                        if (o1.isDirectory() && o2.isFile())
                            return -1;
                        if (o1.isFile() && o2.isDirectory())
                            return 1;
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                for (File f : file.listFiles()) {
                    watcherRecords.addAll(loadDataByFile(f, condition));
                }
                return watcherRecords;
            } else {
                return loadDataByFile(file, condition);
            }
        } catch (Exception e) {
            logger.error("Load Watcher Record by LogPath Err.", e);
        }
        return null;
    }

    //不递归了
    private List<WatcherRecord> loadDataByFile(File file, WatcherDataCondition condition) {
        final List<WatcherRecord> watcherRecords = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            if (file.isDirectory()) {
                return null;
            }
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (null == line || "" == line.trim()) {
                    break;
                }
                if (line.indexOf("LoggerWatcherSubscriber") < 0) {
                    continue;
                }
                if (line.indexOf("INFO") < 0) {
                    continue;
                }
                String subInfo = line.substring(line.indexOf("LoggerWatcherSubscriber") + "LoggerWatcherSubscriber".length() + 3);
                final WatcherRecord watcherRecord = JSON.parseObject(subInfo, WatcherRecord.class);
                if (condition.isCanWatcherId()) {
                    if (!condition.getWatcherId().equals(watcherRecord.getWatcherId())) {
                        continue;
                    }
                }
                if (condition.isCanDate()) {
                    if (null != condition.getStartDate()) {
                        if (watcherRecord.getDate().before(condition.getStartDate())) {
                            continue;
                        }
                    }
                    if (null != condition.getEndDate()) {
                        if (watcherRecord.getDate().after(condition.getEndDate())) {
                            continue;
                        }
                    }
                }
                condition.getCounter().incrementAndGet();
                if (condition.getCounter().get() > MAX_RECORD_SIZE_LOADER) {
                    break;
                }
                watcherRecords.add(watcherRecord);
            }
        } catch (Exception e) {
            logger.error("Load Watcher Record by File Err.", e);
        } finally {
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                }
            }
        }
        return watcherRecords;
    }
}
