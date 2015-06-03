package com.dangdang.digital.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dangdang.digital.model.ActivityCacheVo;
import com.dangdang.digital.model.ActivitySaleCacheVo;
import com.dangdang.digital.model.AnnouncementsCategory;
import com.dangdang.digital.model.AnnouncementsContent;
import com.dangdang.digital.model.Author;
import com.dangdang.digital.model.Catetory;
import com.dangdang.digital.model.Chapter;
import com.dangdang.digital.model.Column;
import com.dangdang.digital.model.Discovery;
import com.dangdang.digital.model.ListCategory;
import com.dangdang.digital.model.Media;
import com.dangdang.digital.model.MediaSale;
import com.dangdang.digital.model.Notice;
import com.dangdang.digital.model.SpecialTopic;
import com.dangdang.digital.model.UserAuthority;
import com.dangdang.digital.model.UserMonthly;
import com.dangdang.digital.model.WorshipRecord;
import com.dangdang.digital.utils.TupleUtils.ResultTwoTuple;
import com.dangdang.digital.vo.AuthorCacheVo;
import com.dangdang.digital.vo.CatetoryMediaSaleVo;
import com.dangdang.digital.vo.ChapterCacheBasicVo;
import com.dangdang.digital.vo.ChapterCacheWholeVo;
import com.dangdang.digital.vo.ContentsVo;
import com.dangdang.digital.vo.MediaCacheBasicVo;
import com.dangdang.digital.vo.MediaCacheWholeVo;
import com.dangdang.digital.vo.MediaSaleCacheVo;
import com.dangdang.digital.vo.UserAuthorityCacheVo;
import com.dangdang.digital.vo.UserMonthlyCacheVo;
import com.dangdang.digital.vo.UserTradeBaseVo;

/**
 * 
 * Description: 缓存api All Rights Reserved.
 * 
 * @version 1.0 2014年12月8日 下午4:26:22 by 许文轩（xuwenxuan@dangdang.com）创建
 */
public interface ICacheApi {
	
	
	/**
	 * 
	 * Description: type:0-获取custId膜拜过的用户列表;type:1-获取膜拜过custId的用户列表
	 * 
	 * @Version1.0 2015年3月16日 下午3:36:44 by 吕翔  (lvxiang@dangdang.com) 创建
	 * @param custId
	 * @param type
	 * @return
	 */
	public  ResultTwoTuple<Integer,List<WorshipRecord>> getWorshipRecord(final int start,final int end,Long custId, String type);
	/**
	 * 
	 * Description: 返回上次请求之后有效的公告列表
	 * @Version1.0 2015年1月17日 上午9:26:02 by 吕翔  (lvxiang@dangdang.com) 创建
	 * @param lastRequestTime 上次请求时间
	 * @return
	 */
	public List<Notice> getNoticeList(long lastRequestTime);
	
	/**
	 * 
	 * Description: 根据公告类型查询该公告类型的公告内容
	 * @Version1.0 2015年1月6日 下午7:27:40 by 吕翔  (lvxiang@dangdang.com) 创建
	 * @param categoryCode
	 * @return
	 */
	public List<AnnouncementsContent> getAnnouncementsContentFromCache(String categoryCode);
	
	/**
	 * 
	 * Description: 根据公告类型查询该公告类型内容
	 * @Version1.0 2015年1月6日 下午7:28:18 by 吕翔  (lvxiang@dangdang.com) 创建
	 * @param categoryCode
	 * @return
	 */
	public AnnouncementsCategory getAnnouncementsCategoryFromCache(String categoryCode);
	
	/**
	 * 
	 * Description: 根据media分类和维度标识查询其分类下来的media_sale并按维度排序
	 * 
	 * @Version1.0 2014年12月27日 上午10:49:33 by 吕翔 (lvxiang@dangdang.com) 创建
	 * @param start
	 * @param end
	 * @param categoryCode
	 *            分类标识
	 * @param dimension
	 *            排序维度
	 * @return
	 * @throws Exception
	 */
	public List<MediaSaleCacheVo> getCategoryMediaSaleByCodeFromCache(final int start, final int end,
			final String categoryCode, final String dimension) throws Exception;

	/**
	 * 
	 * Description: 返回火辣限免结束时间
	 * 
	 * @Version1.0 2014年12月26日 下午4:38:22 by 吕翔 (lvxiang@dangdang.com) 创建
	 * @param activeId
	 * @return
	 */
	public long getHotFreeEndTimeFromCache(int activityId);

	/**
	 * 
	 * Description:
	 * 
	 * @Version1.0 2014年12月26日 下午4:38:57 by 吕翔 (lvxiang@dangdang.com) 创建
	 * @param activeId
	 * @return
	 * @throws Exception
	 */
	public List<MediaSaleCacheVo> getHotFreeFromCache(int start, int end, int activityId) throws Exception;

	/**
	 * 
	 * Description: 通过栏目标识,获取该栏目指定区间的销售主体信息
	 * 
	 * @Version1.0 2014年12月26日 下午12:01:36 by 吕翔 (lvxiang@dangdang.com) 创建
	 * @param start
	 * @param end
	 * @param columnType
	 * @return
	 * @throws Exception
	 */
	public ResultTwoTuple<Long,List<MediaSaleCacheVo>> getMediaSaleByColumnCode(int start, int end, String columnType) throws Exception;
	
	/**
	 * 
	 * Description: 根据media频道编号,查询获取该频道下所有分类信息,并根据参数withdata决定是否返回每个分类销售topn数据
	 * @Version1.0 2015年1月8日 上午11:31:49 by 吕翔  (lvxiang@dangdang.com) 创建
	 * @param withData 是否需要数据
	 * @param offset
	 * @param count
	 * @param catetoryCode
	 * @return
	 * @throws Exception
	 */
	public List<CatetoryMediaSaleVo>  getMediaSaleByCatetoryCode(boolean withData,int start, int end, String catetoryCode)throws Exception;
	/**
	 * 
	 * Description: 通过榜单标识,获取该榜单指定区间的销售主体信息
	 * 
	 * @Version1.0 2014年12月26日 下午12:01:36 by 吕翔 (lvxiang@dangdang.com) 创建
	 * @param offset
	 * @param count
	 * @param listType
	 *            榜单标识
	 * @return
	 * @throws Exception
	 */
	public List<MediaSaleCacheVo> getMediaSaleByRankingListCode(int offset, int count, String listType)
			throws Exception;

	/**
	 * 
	 * Description: 返回栏目内容的总数
	 * 
	 * @Version1.0 2014年12月26日 下午1:56:32 by 吕翔 (lvxiang@dangdang.com) 创建
	 * @param columnCode
	 * @return
	 */
	public long getColumnContentCount(String columnCode);

	/**
	 * 
	 * Description: 返回分类的总数量
	 * 
	 * @Version1.0 2014年12月27日 上午11:35:07 by 吕翔 (lvxiang@dangdang.com) 创建
	 * @param categoryCode 分类标识
	 * @param dimension	   排序维度
	 * @return
	 */
	public long getCategoryCount(String categoryCode,String dimension);

	/**
	 * 
	 * Description: 返回榜单内容的总数
	 * 
	 * @Version1.0 2014年12月26日 下午1:56:32 by 吕翔 (lvxiang@dangdang.com) 创建
	 * @param columnCode
	 * @return
	 */
	public long getRankingListCount(String listType);

	/**
	 * 
	 * Description: 根据分类标识查询media分类信息
	 * 
	 * @Version1.0 2014年12月15日 上午11:44:55 by 吕翔 (lvxiang@dangdang.com) 创建
	 * @param code
	 *            分类标识
	 * @return Catetory
	 */
	public Catetory getCatetoryFromCache(String code);

	/**
	 * 
	 * Description: 根据父分类标识,查询该分类下所有的分类信息
	 * 
	 * @Version1.0 2014年12月25日 上午11:16:28 by 吕翔 (lvxiang@dangdang.com) 创建
	 * @param parentCode
	 *            父分类标识
	 * @return
	 */
	public List<Catetory> getCatetoryListFromCache(String parentCode);

	/**
	 * 
	 * Description: 从缓存中读取榜单信息,如果缓存中没有,则从数据库读取
	 * 
	 * @Version1.0 2014年12月15日 上午9:59:24 by 吕翔 (lvxiang@dangdang.com) 创建
	 * @param listType
	 *            榜单类型标识
	 * @return 榜单实体
	 */
	public ListCategory getListCategoryFromCache(String listType);

	/**
	 * 
	 * Description: 根据栏目编号从缓存中查询到栏目信息,如果缓存中没有,则从数据库中查询并添加到缓存中
	 * 
	 * @Version1.0 2014年12月13日 上午10:18:28 by 吕翔 (lvxiang@dangdang.com) 创建
	 * @param columnType
	 * @return
	 */
	public Column getColumnFromCache(String columnType);

	/**
	 * 
	 * Description: 设置销售主体缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:23:33 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param mediaSale
	 */
	public void setMediaSaleCache(MediaSale mediaSale) throws Exception;

	/**
	 * 
	 * Description: 批量设置销售主体的缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:32:08 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param mediaSaleList
	 */
	public void batchSetMediaSaleCache(List<MediaSale> mediaSaleList) throws Exception;

	/**
	 * 
	 * Description: 设置media缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:24:25 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param media
	 */
	public void setMediaCache(Media media) throws Exception;

	/**
	 * 
	 * Description: 批量设置media缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:33:25 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param mediaList
	 */
	public void batchSetMediaCache(List<Media> mediaList) throws Exception;

	/**
	 * 
	 * Description: 设置章节缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:25:26 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param chapter
	 */
	public void setChapterCache(Chapter chapter) throws Exception;

	/**
	 * 
	 * Description:批量设置章节缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:34:16 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param chapterList
	 */
	public void batchSetChapterCache(List<Chapter> chapterList) throws Exception;

	/**
	 * 
	 * Description: 通过销售主体id获取销售主体缓存（包含media列表），如果缓存中没有则从数据库中取并放入缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:27:32 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param saleId
	 * @return
	 */
	public MediaSaleCacheVo getMediaSaleFromCache(Long saleId) throws Exception;

	/**
	 * 
	 * Description: 批量通过销售主体id获取销售主体缓存（包含media列表），如果缓存中没有则从数据库中取并放入缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:35:28 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param saleIdList
	 * @return
	 */
	public List<MediaSaleCacheVo> batchGetMediaSaleFromCache(List<Long> saleIdList) throws Exception;

	/**
	 * 
	 * Description: 批量通过销售主体id获取销售主体缓存（包含media列表）,如果缓存中没有则从数据库中取并放入缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:35:28 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param saleIdList
	 * @return
	 */

	/**
	 * 
	 * Description: 通过mediaid从缓存获取获取media基本信息
	 * 
	 * @Version1.0 2014年12月8日 下午3:38:19 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param mediaId
	 * @return
	 */
	public MediaCacheBasicVo getMediaBasicFromCache(Long mediaId) throws Exception;

	/**
	 * 
	 * Description: 批量通过mediaid从缓存获取media基本信息
	 * 
	 * @Version1.0 2014年12月8日 下午3:39:12 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param mediaIdList
	 * @return
	 */
	public List<MediaCacheBasicVo> batchGetMediaBasicFromCache(List<Long> mediaIdList) throws Exception;

	/**
	 * 
	 * Description: 通过mediaid从缓存获取media全部信息
	 * 
	 * @Version1.0 2014年12月8日 下午3:40:16 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param mediaId
	 * @return
	 */
	public MediaCacheWholeVo getMediaWholeFromCache(Long mediaId) throws Exception;

	/**
	 * 
	 * Description: 批量通过mediaid从缓存获取media全部信息
	 * 
	 * @Version1.0 2014年12月8日 下午3:42:10 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param mediaIdList
	 * @return
	 */
	public List<MediaCacheWholeVo> batchGetMediaWholeFromCache(List<Long> mediaIdList) throws Exception;

	/**
	 * 
	 * Description: 通过chapterid从缓存获取获取chapter基本信息
	 * 
	 * @Version1.0 2014年12月8日 下午3:38:19 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param chapterId
	 * @return
	 */
	public ChapterCacheBasicVo getChapterBasicFromCache(Long chapterId) throws Exception;

	/**
	 * 
	 * Description: 批量通过chapterid从缓存获取chapter基本信息
	 * 
	 * @Version1.0 2014年12月8日 下午3:39:12 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param chapterIdList
	 * @return
	 */
	public List<ChapterCacheBasicVo> batchGetChapterBasicFromCache(List<Long> chapterIdList) throws Exception;

	/**
	 * 
	 * Description: 通过chapterid从缓存获取chapter全部信息
	 * 
	 * @Version1.0 2014年12月8日 下午3:40:16 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param chapterId
	 * @return
	 */
	public ChapterCacheWholeVo getChapterWholeFromCache(Long chapterId) throws Exception;

	/**
	 * 
	 * Description: 批量通过chapterid从缓存获取chapter全部信息
	 * 
	 * @Version1.0 2014年12月8日 下午3:42:10 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param chapterIdList
	 * @return
	 */
	public List<ChapterCacheWholeVo> batchGetChapterWholeFromCache(List<Long> chapterIdList) throws Exception;

	/**
	 * 
	 * Description: 删除销售主体缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:46:37 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param saleId
	 */
	public void deleteMediaSaleCache(Long saleId) throws Exception;

	/**
	 * 
	 * Description: 批量删除销售主体缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:47:10 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param saleId
	 */
	public void batchDeleteMediaSaleCache(List<Long> saleIdList) throws Exception;

	/**
	 * 
	 * Description: 清除所有销售主体缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:48:54 by 许文轩（xuwenxuan@dangdang.com）创建
	 */
	public void cleanMediaSaleCache() throws Exception;

	/**
	 * 
	 * Description: 删除media基本信息缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:53:28 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param mediaId
	 */
	public void deleteMediaBasicCache(Long mediaId) throws Exception;

	/**
	 * 
	 * Description: 批量删除media基本信息缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:54:14 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param mediaIdList
	 */
	public void batchDeleteMediaBasicCache(List<Long> mediaIdList) throws Exception;

	/**
	 * 
	 * Description: 清除所有media基本信息缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:56:22 by 许文轩（xuwenxuan@dangdang.com）创建
	 */
	public void cleanMediaBasicCache() throws Exception;

	/**
	 * 
	 * Description: 删除media全部信息缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:53:28 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param mediaId
	 */
	public void deleteMediaWholeCache(Long mediaId) throws Exception;

	/**
	 * 
	 * Description: 批量删除media全部信息缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:54:14 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param mediaIdList
	 */
	public void batchDeleteMediaWholeCache(List<Long> mediaIdList) throws Exception;

	/**
	 * 
	 * Description: 清除所有media全部信息缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:56:22 by 许文轩（xuwenxuan@dangdang.com）创建
	 */
	public void cleanMediaWholeCache() throws Exception;

	/**
	 * 
	 * Description: 删除chapter基本信息缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:53:28 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param chapterId
	 */
	public void deleteChapterBasicCache(Long chapterId) throws Exception;

	/**
	 * 
	 * Description: 批量删除chapter基本信息缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:54:14 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param chapterIdList
	 */
	public void batchDeleteChapterBasicCache(List<Long> chapterIdList) throws Exception;

	/**
	 * 
	 * Description: 清除所有chapter基本信息缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:56:22 by 许文轩（xuwenxuan@dangdang.com）创建
	 */
	public void cleanChapterBasicCache() throws Exception;
	
	/**
	 * 
	 * Description: 消除所有栏目的缓存
	 * @Version1.0 2015年1月4日 上午11:15:56 by 吕翔  (lvxiang@dangdang.com) 创建
	 * @throws Exception
	 */
	public void cleanColumnCachce()throws Exception;
	
	/**
	 * 
	 * Description: 消除指定栏目的缓存
	 * @Version1.0 2015年1月4日 上午11:15:56 by 吕翔  (lvxiang@dangdang.com) 创建
	 * @throws Exception
	 */
	public void cleanColumnCachce(String columnCode)throws Exception;
	
	/**
	 * 
	 * Description: 清楚指定系统参数的缓存
	 * @Version1.0 2015年1月13日 上午10:29:59 by 吕翔  (lvxiang@dangdang.com) 创建
	 * @param key
	 * @throws Exception
	 */
	public void cleanSystemPropertyCache(String key)throws Exception;
	
	/**
	 * 
	 * Description: 消除所有榜单的缓存
	 * @Version1.0 2015年1月4日 上午11:17:35 by 吕翔  (lvxiang@dangdang.com) 创建
	 * @throws Exception
	 */
	public void cleanListRankingCache()throws Exception;
	
	
	/**
	 * 
	 * Description: 消除指定榜单的缓存
	 * @Version1.0 2015年1月4日 上午11:17:35 by 吕翔  (lvxiang@dangdang.com) 创建
	 * @throws Exception
	 */
	public void cleanListRankingCache(String listType)throws Exception;
	
	
	
	/**
	 * Description: 清楚所有壕赏榜单的缓存
	 * @Version1.0 2015年1月23日 上午11:59:58 by 周伟洋（zhouweiyang@dangdang.com）创建
	 * @throws Exception
	 */
	public void cleanRewardRankCache()throws Exception;
	
	/**
	 * Description: 清楚指定壕赏榜单的缓存
	 * @Version1.0 2015年1月23日 下午1:46:46 by 周伟洋（zhouweiyang@dangdang.com）创建
	 * @param listType
	 * @throws Exception
	 */
	public void cleanRewardRankCache(String listType)throws Exception;
	
	
	/**
	 * 
	 * Description: 清除所有media二级分类的缓存
	 * @Version1.0 2015年1月4日 上午11:19:21 by 吕翔  (lvxiang@dangdang.com) 创建
	 * @throws Exception
	 */
	public void cleanMediaCategoryCache()throws Exception;
	
	
	/**
	 * 
	 * Description: 清除指定media二级分类的缓存
	 * @Version1.0 2015年1月4日 上午11:19:21 by 吕翔  (lvxiang@dangdang.com) 创建
	 * @throws Exception
	 */
	public void cleanMediaCategoryCache(String categoryCode)throws Exception;
	/**
	 * 
	 * Description: 删除chapter全部信息缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:53:28 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param chapterId
	 */
	public void deleteChapterWholeCache(Long chapterId) throws Exception;

	/**
	 * 
	 * Description: 批量删除chapter全部信息缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:54:14 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param chapterIdList
	 */
	public void batchDeleteChapterWholeCache(List<Long> chapterIdList) throws Exception;

	/**
	 * 
	 * Description: 清除所有chapter全部信息缓存
	 * 
	 * @Version1.0 2014年12月8日 下午3:56:22 by 许文轩（xuwenxuan@dangdang.com）创建
	 */
	public void cleanChapterWholeCache() throws Exception;

	/**
	 * 
	 * Description: 通过token获取用户信息，优先从缓存里取（只包含custId，username和email）
	 * 
	 * @Version1.0 2014年12月11日 上午9:19:48 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param token
	 * @return
	 */
	public UserTradeBaseVo getUserInfoByToken(String token);

	/**
	 * 
	 * Description: 通过token获取用户全部信息信息，优先从缓存里取
	 * 
	 * @Version1.0 2014年12月23日 上午11:24:26 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param token
	 * @return
	 */
	public UserTradeBaseVo getUserWholeInfoByToken(String token);

	/**
	 * 
	 * Description: 增加作者缓存
	 * 
	 * @Version1.0 2014年12月13日 上午11:34:52 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param author
	 * @throws Exception
	 */
	public void setAuthorCache(Author author) throws Exception;

	/**
	 * 
	 * Description: 批量增加作者缓存
	 * 
	 * @Version1.0 2014年12月13日 上午11:35:03 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param authorList
	 * @throws Exception
	 */
	public void batchSetAuthorCache(List<Author> authorList) throws Exception;

	/**
	 * 
	 * Description: 获取作者缓存，如果没有从数据库中读取
	 * 
	 * @Version1.0 2014年12月13日 上午11:35:13 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param authorId
	 * @return
	 * @throws Exception
	 */
	public AuthorCacheVo getAuthorFromCache(Long authorId) throws Exception;

	/**
	 * 
	 * Description: 批量获取作者缓存，如果没有从数据库中读取
	 * 
	 * @Version1.0 2014年12月13日 上午11:35:29 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param authorIdList
	 * @return
	 * @throws Exception
	 */
	public List<AuthorCacheVo> batchGetAuthorFromCache(List<Long> authorIdList) throws Exception;

	/**
	 * 
	 * Description: 删除作者缓存
	 * 
	 * @Version1.0 2014年12月13日 上午11:35:49 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param authorId
	 * @throws Exception
	 */
	public void deleteAuthorCache(Long authorId) throws Exception;

	/**
	 * 
	 * Description: 批量删除作者缓存
	 * 
	 * @Version1.0 2014年12月13日 上午11:35:58 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param authorIdList
	 * @throws Exception
	 */
	public void batchDeleteAuthorCache(List<Long> authorIdList) throws Exception;

	/**
	 * 
	 * Description: 清空作者缓存
	 * 
	 * @Version1.0 2014年12月13日 上午11:36:11 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @throws Exception
	 */
	public void cleanAuthorCache() throws Exception;

	/**
	 * 
	 * Description: 获取章节Id列表，如果没有从数据库里读取，并设置缓存
	 * 
	 * @Version1.0 2014年12月15日 下午6:21:07 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param mediaId
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Long> getChapterIdsFromCache(Long mediaId, Integer start, Integer end);

	/**
	 * 
	 * Description: 获取章节数量，如果没有从数据库里读取，并设置缓存
	 * 
	 * @Version1.0 2014年12月15日 下午6:21:47 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param mediaId
	 * @return
	 */
	public int getChapterCountFromCache(Long mediaId);

	/**
	 * 
	 * Description: 根据custId和mediaId查询权限
	 * 
	 * @Version1.0 2014年12月30日 下午7:55:37 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param custId
	 * @param mediaId
	 * @return
	 */
	public UserAuthorityCacheVo getUserAuthorityCacheVoByCustIdAndMediaId(Long custId, Long mediaId);

	public void setUserAuthorityCacheVo(UserAuthority userAuthority);

	public UserMonthlyCacheVo getUserMonthlyCacheVoByCustId(Long custId);

	public void updateUserMonthlyCacheVo(List<UserMonthly> userMonthlys);

	/**
	 * 
	 * Description: 从缓存获取目录
	 * 
	 * @Version1.0 2014年12月16日 上午10:56:07 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param mediaId
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public Map<Long, ContentsVo> getContentsFromCache(Long mediaId, Integer start, Integer end) throws Exception;

	/**
	 * 
	 * Description: 通过用户Id获取用户权限
	 * 
	 * @Version1.0 2014年12月23日 下午4:13:48 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param custId
	 * @return
	 */
	public List<UserAuthorityCacheVo> getUserAuthorityCacheVoByCustId(Long custId);

	/**
	 * 
	 * Description: 发现设置缓存
	 * 
	 * @Version1.0 2014年12月23日 下午6:21:47 by 杜亚锋（duayfeng@dangdang.com）创建
	 * @param dis
	 * @return
	 */
	public void setDiscoveryCache(Discovery dis);

	/**
	 * 
	 * Description: 从缓存中获取发现数据
	 * 
	 * @Version1.0 2014年12月23日 下午6:21:47 by 杜亚锋（duayfeng@dangdang.com）创建
	 * @param discoveryId
	 * @return
	 */
	public Discovery getDisCoveryFromCache(Long discoveryId);

	/**
	 * 
	 * Description: 从缓存中删除发现数据
	 * 
	 * @Version1.0 2014年12月23日 下午6:21:47 by 杜亚锋（duayfeng@dangdang.com）创建
	 * @param discoveryId
	 * @return
	 */
	public void deleteDiscoveryFromCache(Long discoveryId);

	/**
	 * 
	 * Description: 获取上架状态的saleIdList
	 * 
	 * @Version1.0 2014年12月30日 下午3:37:42 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param saleIdList
	 * @return
	 */
	public List<Long> getOnShelfSaleIdList(List<Long> saleIdList);
	
	
	/**
	 * Description: 删除用户的cache信息
	 * @Version1.0 2014年12月25日 上午11:21:34 by 周伟洋（zhouweiyang@dangdang.com）创建
	 * @param custId
	 */
	void deleteActivityUserVoCache(Long custId);
	
	/**
	 * Description: 删除所有用户活动cache信息
	 * @Version1.0 2015年1月29日 上午10:56:31 by 周伟洋（zhouweiyang@dangdang.com）创建
	 */
	void cleanActivityUserVoCache();
	
	
	/**
	 * Description:  删除用户今天的信息的活动cache信息
	 * @Version1.0 2015年1月5日 下午5:16:29 by 周伟洋（zhouweiyang@dangdang.com）创建
	 * @param custId
	 */
	void deleteTodayActivityUserCache(Long custId);
	
	
	/**
	 * Description:  删除块的cache信息
	 * @Version1.0 2015年1月5日 下午5:16:46 by 周伟洋（zhouweiyang@dangdang.com）创建
	 * @param code
	 */
	void deleteBlockCache(String code);
	
	/**
	 * Description:  删除书的打赏列表的cache信息
	 * @Version1.0 2015年1月5日 下午5:16:59 by 周伟洋（zhouweiyang@dangdang.com）创建
	 * @param mediaId
	 * @param channel
	 */
	void deleteEbookRewardedUsersCache(Long mediaId,String channel);
	
	/**
	 * Description:  删除用户打赏书的cache信息
	 * @Version1.0 2015年1月5日 下午5:17:15 by 周伟洋（zhouweiyang@dangdang.com）创建
	 * @param custId
	 */
	void deleteUserRewardBooksIdsCache(Long custId);

	/**
	 * Description:  删除奖品列表的cache信息
	 * @Version1.0 2015年1月5日 下午5:17:27 by 周伟洋（zhouweiyang@dangdang.com）创建
	 * @param vestType
	 */
	void deletePrizeListCache(int vestType);

	/**
	 * Description: 删除壕赏榜的cache信息
	 * @Version1.0 2015年1月5日 下午5:17:39 by 周伟洋（zhouweiyang@dangdang.com）创建
	 * @param code
	 */
	void deleteRankConsToBookByCodeCache(String code);
	
	/**
	 * 
	 * Description: 刷新活动缓存
	 * 
	 * @Version1.0 2015年1月10日 下午5:56:42 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param activityId
	 */
	public void refreshActivityCache(Integer activityId);

	/**
	 * 
	 * Description: 获取活动缓存，如果没有从数据库里获取
	 * 
	 * @Version1.0 2015年1月10日 下午5:56:57 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param activityId
	 * @return
	 */
	public ActivityCacheVo getActivityFromCache(Integer activityId);

	/**
	 * 
	 * Description: 删除活动缓存
	 * 
	 * @Version1.0 2015年1月10日 下午5:57:19 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param activityId
	 */
	public void deleteActivityCache(Integer activityId);

	/**
	 * 
	 * Description: 批量刷新活动缓存
	 * @Version1.0 2015年1月10日 下午6:39:58 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param activityIdList
	 */
	public void batchRefreshActivityCache(List<Integer> activityIdList);

	/**
	 * 
	 * Description: 批量获取活动缓存，如果为空从数据库里获取
	 * @Version1.0 2015年1月10日 下午6:40:16 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param activityIdList
	 * @return
	 */
	public List<ActivityCacheVo> batchGetActivityFromCache(List<Integer> activityIdList);

	/**
	 * 
	 * Description: 批量删除活动缓存
	 * @Version1.0 2015年1月10日 下午6:40:35 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param activityIdList
	 */
	public void batchDeleteActivityCache(List<Integer> activityIdList);

	/**
	 * 
	 * Description: 通过活动id获取活动和销售主体对应关系缓存，如果没有从数据库里读取
	 * @Version1.0 2015年1月12日 上午10:53:42 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param activityId
	 * @return
	 */
	public List<ActivitySaleCacheVo> getActivitySaleCacheByActivityId(Integer activityId);

	/**
	 * 
	 * Description: 通过销售主体id获取活动和销售主体对印关系缓存，如果没有从数据库里读取
	 * @Version1.0 2015年1月12日 上午10:56:34 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param saleId
	 * @return
	 */
	public List<ActivitySaleCacheVo> getActivitySaleCacheBySaleId(Long saleId);

	/**
	 * 
	 * Description: 通过销售主体id获取活动和销售主体关系缓存（包含活动信息），如果没有从数据库里读取
	 * @Version1.0 2015年1月12日 上午10:59:56 by 许文轩（xuwenxuan@dangdang.com）创建
	 * @param saleId
	 * @return
	 */
	public List<ActivitySaleCacheVo> getActivitySaleCacheWithActivityBySaleId(Long saleId);
	
	/**
	 * 
	 * Description: 通过销售主体id删除活动和销售主体对印关系缓存
	 * @Version1.0 2015年1月17日 下午6:08:57 by 张宪斌（zhangxianbin@dangdang.com）创建
	 * @param saleId
	 */
	public void deleteActivitySaleCacheBySaleId(Long saleId);
	/**
	 * 
	 * Description: 通过销售主体id集合批量删除活动和销售主体对印关系缓存
	 * @Version1.0 2015年1月17日 下午6:09:01 by 张宪斌（zhangxianbin@dangdang.com）创建
	 * @param saleIdList
	 */
	public void mDeleteActivitySaleCacheBySaleId(List<Long> saleIdList);
	
	/**
	 * 
	 * Description: 清除活动缓存
	 * @Version1.0 2015年1月12日 上午11:58:18 by 许文轩（xuwenxuan@dangdang.com）创建
	 */
	public void cleanActivityCache();
	
	/**
	 * 
	 * Description: 清除指定key的缓存
	 * 
	 * @Version1.0 2015年1月4日 上午11:22:47 by 吕翔 (lvxiang@dangdang.com) 创建
	 * @param cacheKey
	 */
	public void cleanCacheByKey(String cacheKey);
	
	/**
	 * Description: 获取用的cache信息，通过custid
	 * @Version1.0 2015年1月29日 下午1:41:30 by 周伟洋（zhouweiyang@dangdang.com）创建
	 * @param custId
	 * @return
	 */
	Map<String,String> getUserInfoByCustId(Long custId);
	
	/**
	 * Description: 通过stId查找缓存，没有专题再找数据库，数据库也没有返回null；stId为空时返回首页专题
	 * All Rights Reserved.
	 * @version 1.0  2015年3月20日 上午11:32:07  by yangzhenping（yangzhenping@dangdang.com）创建
	 * @param stId
	 * @param deviceType
	 * @param channelType
	 * @return
	 */
	public List<SpecialTopic> getSpecialTopicFromCache(String stId, String deviceType, String channelType);
	/**
	 * Description: 清楚专题缓存
	 * All Rights Reserved.
	 * @version 1.0  2015年3月20日 下午8:02:12  by yangzhenping（yangzhenping@dangdang.com）创建
	 * @param stIds
	 */
	public void cleanSpecialTopicCache(Set<String> cacheKeys);
	
	/**
	 * Description: 通过custIds 批量获取用户信息
	 * @Version1.0 2015年3月13日 下午5:43:06 by 魏嵩（weisong@dangdang.com）创建
	 * @param custIdList
	 * @return
	 */
	public Map<Long, UserTradeBaseVo> getUserInfoByCustIds(List<Long> custIdList);

	/**
	 * Description: 通过custId获取用户信息map
	 * @Version1.0 2015年3月13日 下午5:43:06 by 魏嵩（weisong@dangdang.com）创建
	 * @param custId
	 * @return
	 */
	public UserTradeBaseVo getUserWholeInfoByCustId(Long custId);

}
