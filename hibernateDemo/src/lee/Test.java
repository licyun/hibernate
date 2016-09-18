package lee;

import org.crazyit.app.domain.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class Test {
	public static void main(String[] args)
			throws Exception
		{
			//ʵ����Configuration��
			Configuration conf = new Configuration()
			//���淽��Ĭ�ϼ���hibernate.cfg.xml�ļ�
				.configure();
			//��Configuration����SessionFactory
			SessionFactory sf = conf.buildSessionFactory();
			//����Session
			Session sess = sf.openSession();
			//��ʼ����
			Transaction tx = sess.beginTransaction();
			//������Ϣʵ��
			SchemaExport SchemaExportschemaExport = new SchemaExport(conf);
			SchemaExportschemaExport.create(true,true);
			   System.out.println("Tablecreated.");
			//�ύ����
			tx.commit();
			//�ر�Session
			sess.close();
			sf.close();
		}
}
