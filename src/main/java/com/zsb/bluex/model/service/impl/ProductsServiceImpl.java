package com.zsb.bluex.model.service.impl;

import com.zsb.bluex.model.entity.Products;
import com.zsb.bluex.model.mapper.ProductsMapper;
import com.zsb.bluex.model.service.ProductsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息表，用于存储商品的基本数据 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2025-09-01
 */
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements ProductsService {

}
