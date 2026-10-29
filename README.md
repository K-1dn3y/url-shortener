# URL Shortener 短链接服务

一个基于 Spring Boot 的短链接服务：把长网址转换为短链接，访问短链接自动跳转回原网址。

## 技术栈

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- MySQL 8.0
- Maven

## 功能特性

- ✅ 长网址 → 短链接（Base62 编码）
- ✅ 访问短链接自动跳转（302 重定向）
- ✅ 数据持久化（MySQL，重启不丢失）
- ✅ 统一的异常处理（404 / 400，返回规范 JSON）
- ✅ 参数校验（非法 URL 拦截）

## 项目结构

```
src/main/java/com/example/urlshortener/
├── controller/   # 接口层：接收请求、转发
├── service/      # 业务层：短码生成、跳转逻辑
├── repository/   # 数据层：数据库访问（JPA）
├── model/        # 数据模型：Url 实体
└── exception/    # 全局异常处理
```

## 快速开始

1. 确保已安装 JDK 17、Maven、MySQL 8.0
2. 创建数据库：`CREATE DATABASE url_shortener;`
3. 修改 `application.properties` 里的数据库账号密码
4. 运行：`mvn spring-boot:run`
5. 访问：`http://localhost:8080`

## API 接口

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/shorten?url=长网址` | 缩短，返回短码 |
| GET | `/{shortCode}` | 跳转到原始长网址 |

**示例**：

```bash
# 缩短
curl -X POST "http://localhost:8080/shorten?url=https://www.baidu.com"
# 返回：1

# 跳转
curl -i "http://localhost:8080/1"
# 返回：302 + Location: https://www.baidu.com
```

## 核心设计

- **短码生成**：数据库自增 ID → Base62 编码（62 个字符 `0-9a-zA-Z`，永不碰撞）
- **三层架构**：Controller → Service → Repository，各司其职
- **全局异常处理**：`@RestControllerAdvice` 统一处理异常，返回规范 JSON
