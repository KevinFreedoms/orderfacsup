package com.sr.facorder.common.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.sqlArcher.server.dao.impl.SQLDao;

public abstract class BaseDAO extends SQLDao {

	protected HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	// inject 
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	/** 
	* 使用hql 语句进行操作 
	* @param hql HSQL 查询语句 
	* @param offset 開始取数据的下标 
	* @param length 读取数据记录数 
	* @return List 结果集 
	*/ 
	@SuppressWarnings("rawtypes")
	public List getListForPage(final String hql, final int offset, final int length) { 

		List list = hibernateTemplate.executeFind(new HibernateCallback() { 
		public Object doInHibernate(Session session) 
				throws HibernateException, SQLException { 
				Query query = session.createQuery(hql); 
				query.setFirstResult(offset); 
				query.setMaxResults(length); 
				List list = query.list(); 
				session.close();
				return list; 
			} 
		}); 
		return list; 
	} 
}
