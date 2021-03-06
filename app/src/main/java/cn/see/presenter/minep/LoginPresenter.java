package cn.see.presenter.minep;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;
import cn.see.fragment.fragmentview.mineview.LoginAct;
import cn.see.model.LoginModel;
import cn.see.util.ToastUtil;
import cn.see.util.http.Api;
import cn.see.util.widet.CustomProgress;

/**
 * @日期：2018/6/13
 * @作者： GuoXinBo
 * @邮箱： guoxinbo@banling.com
 * @说明： 登录P
 */

public class LoginPresenter extends XPresent<LoginAct> {

    public CustomProgress progress;

    /**
     * 登录
     *
     * @param account 手机号
     * @param password 密码
     * @param uniquely 设备唯一码
     */
    public void login(String account,String password ,String uniquely){

        progress = CustomProgress.show(getV());
        Api.mineService().goLogin(account,password,uniquely)
                .compose(XApi.<LoginModel>getApiTransformer())
                .compose(XApi.<LoginModel>getScheduler())
                .compose(getV().<LoginModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<LoginModel>() {
                    @Override
                    protected void onFail(NetError error) {
                        progress.dismiss();
                    }

                    @Override
                    public void onNext(LoginModel loginModel) {
                        if(!loginModel.isError()){
                            getV().loginResponse(loginModel);
                         }else{
                            ToastUtil.showToast(loginModel.getErrorMsg());
                        }
                    }
                });
    }

    /**
     * 检查输入是否有误
     * @param phone
     * @param pwd
     */
    public boolean isCheckInput(String phone,String pwd){
        if(phone.length()!=11){
            getV().showInputError("手机号码输入不合格");
            return false;
        }
        if(pwd.equals("")){
            getV().showInputError("密码输入不合格");
            return false;
        }

        return true;
    }
}
