package com.project.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.store.entity.ProductComment;
import com.project.store.mapper.ProductCommentMapper;
import com.project.store.service.ProductCommentService;
import org.springframework.stereotype.Service;

@Service
public class ProductCommentServiceImpl extends ServiceImpl<ProductCommentMapper, ProductComment> implements ProductCommentService {

}
