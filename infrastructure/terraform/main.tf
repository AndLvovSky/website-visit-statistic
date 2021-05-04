terraform {
  backend "s3" {
    bucket = "wvs-terraform-state"
    key = "main.tfstate"
    region = "us-east-1"
  }
}

provider "aws" {
  profile = "default"
  region = "us-east-1"
}

module "s3" {
  source = "./modules/s3"

  website_bucket_name = var.website_bucket_name
}
