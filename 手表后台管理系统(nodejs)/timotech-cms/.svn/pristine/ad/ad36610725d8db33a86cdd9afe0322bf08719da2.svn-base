/**
 * Created by Administrator on 2015/9/17.
 */

var express = require('express');
var adminCtrl = require('./controllers/admin_controller');
var loginCtrl = require('./controllers/login_controller');
var deviceCtrl = require('./controllers/device_controller');
var resourceCtrl = require('./controllers/resource_controller');
var auth = require('./middleware/auth');
var router = express.Router();

router.get('/login', loginCtrl.login);
router.get('/signout', auth.userRequired, loginCtrl.signout);
router.get('/index', auth.userRequired, adminCtrl.index);
router.get('/', auth.userRequired, adminCtrl.index);
router.get('/admin/user', auth.userRequired, adminCtrl.user);
router.get('/admin/updatepswd', auth.userRequired, adminCtrl.updatePswd);
router.get('/admin/organ', auth.userRequired, adminCtrl.organ);

router.get('/device/log', auth.userRequired, deviceCtrl.log);
router.get('/device/watch', auth.userRequired, deviceCtrl.watch);
router.get('/device/device', auth.userRequired, auth.adminRequired, deviceCtrl.device);
router.get('/device/exportLog', auth.userRequired, auth.adminRequired, deviceCtrl.exportLog);
router.get('/device/package', auth.userRequired, auth.adminRequired, deviceCtrl.package);
router.get('/device/update', auth.userRequired, auth.adminRequired, deviceCtrl.update);

router.post('/signin', loginCtrl.signIn);
router.post('/admin/getorganlist', auth.userRequired, adminCtrl.getOrganList);
router.post('/admin/saveorgan', auth.userRequired, auth.adminRequired, adminCtrl.saveOrgan);
router.post('/admin/getdisplayorganlist', auth.userRequired, adminCtrl.getDisplayOrganList);
router.post('/admin/getuserlist', auth.userRequired, adminCtrl.getUserList);
router.post('/admin/saveuser', auth.userRequired, auth.adminRequired, adminCtrl.saveUser);
router.post('/admin/resetpassword', auth.userRequired, auth.adminRequired, adminCtrl.resetPassword);
router.post('/admin/saveupdatepswd', auth.userRequired, adminCtrl.saveUpdatePswd);

router.post('/device/getloglist', auth.userRequired, auth.adminRequired, deviceCtrl.getLogList);
router.post('/device/sendCommand', auth.userRequired, auth.adminRequired, deviceCtrl.sendCommand);
router.post('/device/getdevicelist', auth.userRequired, deviceCtrl.getDeviceList);
router.post('/device/ubind', auth.userRequired, auth.adminRequired, deviceCtrl.unbind);

router.post('/device/getPackagelist', auth.userRequired, auth.adminRequired, deviceCtrl.getPackageList);
router.post('/device/removePackage', auth.userRequired, auth.adminRequired, deviceCtrl.removePackage);
router.post('/device/savePackage', auth.userRequired, auth.adminRequired, deviceCtrl.savePackage);
router.post('/device/updatePackage', auth.userRequired, auth.adminRequired, deviceCtrl.updatePackage);
router.post('/device/uploadPackage', auth.userRequired, auth.adminRequired, deviceCtrl.uploadPackage);

router.post('/device/getUpdate', auth.userRequired, auth.adminRequired, deviceCtrl.getUpdate);
router.post('/device/removeUp', auth.userRequired, auth.adminRequired, deviceCtrl.removeUp);
router.post('/device/updateUp', auth.userRequired, auth.adminRequired, deviceCtrl.updateUp);
router.post('/device/saveUp', auth.userRequired, auth.adminRequired, deviceCtrl.saveUp);

router.post('/device/getContact', auth.userRequired, auth.adminRequired, deviceCtrl.getContact);
router.post('/device/getCallLog', auth.userRequired, auth.adminRequired, deviceCtrl.getCallLog);
router.post('/device/getScene', auth.userRequired, deviceCtrl.getScene);
router.post('/device/getAlarm', auth.userRequired, deviceCtrl.getAlarm);
router.post('/device/getFriends', auth.userRequired, deviceCtrl.getFriends);
router.post('/device/getSetting', auth.userRequired, deviceCtrl.getSetting);

router.get('/resource/audio', auth.userRequired, resourceCtrl.audio);
router.get('/resource/dial', auth.userRequired, resourceCtrl.dial);
router.get('/resource/emoticon', auth.userRequired, resourceCtrl.emoticon);
router.get('/resource/theme', auth.userRequired, resourceCtrl.theme);

router.post('/resource/saveAudio', auth.userRequired, resourceCtrl.saveAudio);
router.post('/resource/saveEmoticon', auth.userRequired, resourceCtrl.saveEmoticon);
router.post('/resource/saveDialAndTheme', auth.userRequired, resourceCtrl.saveDialAndTheme);

router.post('/resource/updateAudio', auth.userRequired, resourceCtrl.updateAudio);
router.post('/resource/updateEmoticon', auth.userRequired, resourceCtrl.updateEmoticon);
router.post('/resource/updateDialAndTheme', auth.userRequired, resourceCtrl.updateDialAndTheme);

router.post('/resource/deleteAudio', auth.userRequired, resourceCtrl.deleteAudio);
router.post('/resource/deleteEmoticon', auth.userRequired, resourceCtrl.deleteEmoticon);
router.post('/resource/deleteDialAndTheme', auth.userRequired, resourceCtrl.deleteDialAndTheme);

router.post('/resource/getAudios', auth.userRequired, resourceCtrl.getAudios);
router.post('/resource/getDials', auth.userRequired, resourceCtrl.getDials);
router.post('/resource/getEmoticons', auth.userRequired, resourceCtrl.getEmoticons);
router.post('/resource/getThemes', auth.userRequired, resourceCtrl.getThemes);

router.post('/resource/publish', auth.userRequired, resourceCtrl.publish);

module.exports = router;
/**/