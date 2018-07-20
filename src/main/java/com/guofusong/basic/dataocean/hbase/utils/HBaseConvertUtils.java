package com.guofusong.basic.dataocean.hbase.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import com.guofusong.basic.dataocean.hbase.po.HBaseRspEntity;


/**
 * @ClassName: HbaseConvertUtils
 * @version 2.0 
 * @Desc: 
 * @author guofusong
 * @date 2018年6月4日下午4:58:35
 * @history v2.0
 *
 */
public class HBaseConvertUtils {

	public static HBaseRspEntity[] convert2Array(Result result){
		if(result == null || result.isEmpty())
			return new HBaseRspEntity[]{};
		Cell[] cells = result.rawCells();
		HBaseRspEntity[] entities = new HBaseRspEntity[cells.length];
		for (int i = 0 ; i < cells.length ; i ++) {
			HBaseRspEntity dataEntity = new HBaseRspEntity();
			dataEntity.setFamily(Bytes.toString(cells[i].getFamilyArray(), cells[i].getFamilyOffset(), cells[i].getFamilyLength()));
			dataEntity.setQualifier(Bytes.toString(cells[i].getQualifierArray(), cells[i].getQualifierOffset(), cells[i].getQualifierLength()));
			dataEntity.setRow(Bytes.toString(cells[i].getRowArray(), cells[i].getRowOffset(), cells[i].getRowLength()));
			dataEntity.setSequenceId(cells[i].getSequenceId());
			dataEntity.setTags(Bytes.toString(cells[i].getTagsArray(), cells[i].getTagsOffset(), cells[i].getTagsLength()));
			dataEntity.setTimestamp(cells[i].getTimestamp());
			dataEntity.setValue(Bytes.toString(cells[i].getValueArray(), cells[i].getValueOffset(), cells[i].getValueLength()));
			entities[i] = dataEntity;
		}
		return entities;
	}
	
	
	
	public static Map<String,List<HBaseRspEntity>> convert2Map(List<Result> results){
		Map<String,List<HBaseRspEntity>> map = new HashMap<>(); 
		if(results == null || results.isEmpty())
			return map;
		List<HBaseRspEntity> entities = new ArrayList<>();
		for(int i = 0; i < results.size(); i++){
			Cell[] cells = results.get(i).rawCells();
			for (Cell cell  : cells) {
				HBaseRspEntity dataEntity = new HBaseRspEntity();
				dataEntity.setFamily(Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()));
				dataEntity.setQualifier(Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()));
				dataEntity.setRow(Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength()));
				dataEntity.setSequenceId(cell.getSequenceId());
				dataEntity.setTags(Bytes.toString(cell.getTagsArray(), cell.getTagsOffset(), cell.getTagsLength()));
				dataEntity.setTimestamp(cell.getTimestamp());
				dataEntity.setValue(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
				entities.add(dataEntity);
			}
		}
		for (HBaseRspEntity hbaseDataEntity : entities) {
			if(map.containsKey(hbaseDataEntity.getRow())){
				map.get(hbaseDataEntity.getRow()).add(hbaseDataEntity);
			}else{
				List<HBaseRspEntity> entities2 = new ArrayList<>();
				entities2.add(hbaseDataEntity);
				map.put(hbaseDataEntity.getRow(), entities2);
			}
		}
		return map;
	}
	
}
