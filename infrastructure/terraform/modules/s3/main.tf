resource "aws_s3_bucket" "website" {
  bucket = var.website_bucket_name
  acl = "public-read"

  website {
    index_document = "index.html"
    error_document = "index.html"
  }
}
