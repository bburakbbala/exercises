using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IHospitalRepository : IRepositoryAsync<Hospital>
    {
        void Update(Hospital hospital);
    }
}
