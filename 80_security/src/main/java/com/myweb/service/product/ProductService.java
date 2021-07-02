package com.myweb.service.product;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.domain.FilesVO;
import com.myweb.domain.PageVO;
import com.myweb.domain.ProductVO;
import com.myweb.persistence.files.FilesDAORule;
import com.myweb.persistence.product.ProductDAORule;

@Service
public class ProductService implements ProductServiceRule {
	private static Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Inject
	private ProductDAORule pdao;
	@Inject
	private FilesDAORule fdao;
	
	@Override
	public int register(ProductVO pvo) {
		return pdao.insert(pvo);
	}

	@Override
	public List<ProductVO> getList(PageVO pgvo) {
		return pdao.selectList(pgvo);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED) // update가 commit된 상황을 select 해라~
	@Override
	public ProductVO detail(int pno) {
		pdao.updateRC(pno, 1);
		ProductVO pvo = pdao.selectOne(pno);
//		int fcnt = fdao.selectOne(pno); // 파일 유무확인
		if(pvo != null) { // 원래는 38번 줄 추가 후 if 조건 fcnt > 0 로 해야됨
			List<FilesVO> flist = fdao.selectList(pno);
			pvo.setFlist(flist);
		}
		return pvo;
	}

	@Override
	public int modify(ProductVO pvo) {
		pdao.updateRC(pvo.getPno(), -2);
		return pdao.update(pvo);
	}

	@Override
	public int remove(int pno) {
		return pdao.delete(pno);
	}

	@Override
	public int getCurrPno() {
		return pdao.selectOne();
	}

	@Override
	public int getTotalCount(PageVO pgvo) {
		return pdao.selectOne(pgvo);
	}

}
