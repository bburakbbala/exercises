using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class CountryOrRegionRepository : RepositoryAsync<CountryOrRegion>, ICountryOrRegionRepository
    {
        private readonly ApplicationDbContext _db;
        public CountryOrRegionRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(CountryOrRegion countryOrRegion)
        {
        }
    }
}
