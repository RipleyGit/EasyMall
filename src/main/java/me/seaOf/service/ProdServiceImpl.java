package me.seaOf.service;

import me.seaOf.bean.Product;
import me.seaOf.dao.ProdDao;
import me.seaOf.factory.BasicFactory;

import java.util.List;

public class ProdServiceImpl implements ProdService {
    private ProdDao prodDao = BasicFactory.getFactory().getInstance(ProdDao.class);
    public List<Product> findAll() {
        return prodDao.findAll();
    }
    public boolean updatePnum(String pid, int pnum) {
        return prodDao.updatePnum(pid, pnum);
    }
    public boolean delProd(String pid) {
        return prodDao.delProd(pid);
    }
    public List<Product> findAllByCondition(String _name, String _category,
                                            double _minprice, double _maxprice) {
        return prodDao.findAllByCondition(_name, _category, _minprice, _maxprice);
    }
    public List<Product> findAllBySearch(String search) {
        return prodDao.findAllBySearch(search);
    }
    public Product findProdById(String pid) {
        return prodDao.findProdById(pid);
    }


}

