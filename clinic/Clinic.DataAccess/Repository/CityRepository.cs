using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;
using System.Linq;

namespace Clinic.DataAccess.Repository
{
    public class CityRepository : RepositoryAsync<City>, ICityRepository
    {
        private readonly ApplicationDbContext _db;
        public CityRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(City city)
        {
            var objFromDb = _db.Cities.FirstOrDefault(d => d.Id == city.Id);
            if (objFromDb != null)
            {
                objFromDb.Name = city.Name;
                objFromDb.CountryOrRegionId = objFromDb.CountryOrRegionId;
            }
        }
    }
}
