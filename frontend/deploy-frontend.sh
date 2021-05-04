# Build static files
NUXT_ENV_BACKEND_URL=http://wvs-lb-1523165624.us-east-1.elb.amazonaws.com npm run build
# Upload static files to S3
aws s3 sync dist s3://website-visit-statistics --delete --acl public-read --cache-control no-cache
