package com.tokid.controller;
/*
* @Description:done
* @author king
* @date 2017/11/24 14:44
*/

import com.tokid.base.exception.BizException;
import com.tokid.base.customUtils.Result;
import com.tokid.base.customUtils.ResultEnum;
import com.tokid.base.utils.StringUtils;
import com.tokid.base.customUtils.JsonRequestBody;
import com.tokid.model.Permission;
import com.tokid.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/permission")
@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/saveOrUpdate")
    public Object saveOrUpdate(@RequestBody JsonRequestBody body) {
        Result<?> result;
        try {
            Permission permission = body.tryGet(Permission.class);
            if (permission == null) {
                throw new BizException("permission is null");
            }
            if (StringUtils.isBlank(permission.getName()))
                throw new BizException("name is null");
            if (StringUtils.isBlank(permission.getResourceType()))
                throw new BizException("resourceType is null");
            if (StringUtils.isBlank(permission.getUrl()))
                throw new BizException("url is null");

            result = Result.createSuccessResultForm(permissionService.saveOrUpdate(permission), ResultEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            result = Result.createErrorResultForm(ResultEnum.ERROR);
        }
        return result;
    }

    @RequestMapping("/delete")
    public Object delete(@RequestBody JsonRequestBody body) {
        Result<?> result;
        try {
            Long id = body.getLong("id");
            if (id == null)
                throw new BizException("id is null");

            result = Result.createSuccessResultForm(permissionService.deleteByPrimaryKey(id), ResultEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            result = Result.createErrorResultForm(ResultEnum.ERROR);
        }
        return result;
    }

    @RequestMapping("/get")
    public Object getById(@RequestBody JsonRequestBody body) {
        Result<?> result;
        try {
            Long id = body.getLong("id");
            if (id == null)
                throw new BizException("id is null");

            result = Result.createSuccessResultForm(permissionService.selectById(id), ResultEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            result = Result.createErrorResultForm(ResultEnum.ERROR);
        }
        return result;
    }

    //获取分页用户列表
    @RequestMapping("/getList")
    public Object getList(@RequestBody JsonRequestBody body) {
        Result<?> result;
        try {
            Permission permission = body.tryGet(Permission.class);
            result = Result.createSuccessResultForm(permissionService.selectPage(body.getPageForm(), permission), ResultEnum.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            result = Result.createErrorResultForm(ResultEnum.ERROR);
        }
        return result;
    }

}
