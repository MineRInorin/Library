package com.lib.demo.mapper;

import com.lib.demo.bean.Brrow;
import com.lib.demo.bean.Tongji;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ibm5
 * @since 2020-11-17
 */
@Repository
public interface BrrowMapper extends BaseMapper<Brrow> {
	List<Brrow> selectBrrowCountPageList(Page page);
	List<Tongji> findRankbyAuthor();
	List<Tongji> findRankbyType();
	List<Tongji> findRankbyMonth();
	List<Tongji> findRankbyBook();
}
