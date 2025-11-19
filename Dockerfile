# Giai đoạn 1: Build file .jar
# Sử dụng Maven image để build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Build project, bỏ qua test để nhanh hơn
RUN mvn clean package -DskipTests

# Giai đoạn 2: Tạo image chạy
# Dùng JDK bản nhẹ (slim) để tối ưu dung lượng
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copy file .jar từ giai đoạn build sang
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]