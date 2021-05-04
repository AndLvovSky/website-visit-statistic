# login to ECR
aws ecr-public get-login-password --region us-east-1 | docker login --username AWS --password-stdin public.ecr.aws/u2x1f6d4
# build application
./gradlew clean build -x test
# build image
docker build -t wvs .
# tag image
docker tag wvs:latest public.ecr.aws/u2x1f6d4/wvs:latest
# push image
docker push public.ecr.aws/u2x1f6d4/wvs:latest
# update ECS service
aws ecs update-service --cluster wvs --service main --force-new-deployment
