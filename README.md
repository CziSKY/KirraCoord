# KirraCoord

> 作者: @kirraObj (咸蛋)

简单且轻量级的公共点插件, 基于 Kotlin & TabooLib 6 进行开发。

支持 WorldGuard 的区域黑名单。

## 开发接口

### 事件

- CoordTeleportEvent - 当玩家准备传送到某个点时触发。

## 指令

```text
  /coord ...

  set (coordName) - 设置一个点。
  del (coordName) - 删除一个点。
  list - 查看已经创建的点列表。
  tp (coordName) (playerName?) - 将一名玩家传送至某个点。
```

## 版本更新

### 1.0.0-SNAPSHOT

完善所有基本功能