# Build static files
NUXT_ENV_BACKEND_URL=https://wvs-backend.andlvovsky.com npm run build
# Upload static files to S3
aws s3 sync dist s3://website-visit-statistics --delete --acl public-read --cache-control no-cache
